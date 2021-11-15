package generator;

import com.psbc.pojo.AccountApplication;

public interface AccountApplicationDao {
    int deleteByPrimaryKey(String appsheetserialno);

    int insert(AccountApplication record);

    int insertSelective(AccountApplication record);

    AccountApplication selectByPrimaryKey(String appsheetserialno);

    int updateByPrimaryKeySelective(AccountApplication record);

    int updateByPrimaryKey(AccountApplication record);
}