package com.psbc.utils.helper;

import com.psbc.utils.StringProcessor;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CodeWriterFromDDL {

    private static Map<String,String> typeMapper = new HashMap<String,String>(){
        {
            put("VARCHAR","String");
            put("INT","int");
            put("DECIMAL","Double");
        }
    };

    private static String className = "";

    public static void main(String[] args) {
        String ddlPath = "./src/main/resources/zhang/target/sql/transaction_expectation";
        String classString = getClassString(ddlPath);
        String filePath = "./src/main/resources/zhang/target/po/"+className+".java";
        write(filePath, classString);
    }

    private static String getClassString(String path){
        final String annotation =
                "import lombok.AllArgsConstructor;\n" +
                        "import lombok.Data;\n" +
                        "import lombok.NoArgsConstructor;\n\n"+
                        "@Data\n@AllArgsConstructor\n@NoArgsConstructor\n";

        final String PRIVATE = "private";
        final String PUBLIC = "public";

        StringBuilder re = new StringBuilder(annotation);
        re.append(PUBLIC);
        re.append(" class ");

        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line = null;
            int start = 2;
            int lineIndex = 0;
            while ((line = reader.readLine()) != null){
                if(lineIndex == 0){
                    String rowClassName = line.split(" ")[2].split(";")[0];
                    className = StringProcessor.upperFirstCase(rowClassName.substring(0,rowClassName.indexOf("_"))) +
                            StringProcessor.upperFirstCase(rowClassName.substring(rowClassName.indexOf("_") + 1));
                    re.append(className);
                    re.append(" {\n");
                }
                if(lineIndex < start){
                    lineIndex++;
                    continue;
                }
                String fieldName = line.trim().split("\\s+")[0];
                String fieldType = line.trim().split("\\s+")[1];
                if(fieldType.indexOf("(") > 0){
                    fieldType = fieldType.substring(0, fieldType.indexOf("("));
                }
                if(typeMapper.get(fieldType) != null) {
                    re.append("\t").
                            append(PRIVATE).
                            append(" ").
                            append(typeMapper.get(fieldType)).
                            append(" ").
                            append(fieldName).
                            append(";\n");
                }
            }
            re.append("}");
            return re.toString();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static void write(String filepath, String content){
        try {
            File f = new File(filepath);
            if (!f.exists()) {
                f.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f));
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(content);
            writer.flush();
            write.close();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}