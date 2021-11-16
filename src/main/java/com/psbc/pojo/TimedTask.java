package com.psbc.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * timed_task
 * @author 
 */
@Data
public class TimedTask implements Serializable {
    /**
     * 定时任务ID
     */
    private Integer taskid;

    /**
     * 任务名字（即包名+类名+方法名）
     */
    private String taskname;

    /**
     * 任务时间（最早时间，HHMMSS）
     */
    private String tasktime;

    /**
     * 是否必须
     */
    private Object necessity;

    /**
     * 前置任务ID（可以为多个）
     */
    private String dependency;

    /**
     * 是否完成标志（每日由脚本完成更新）
     */
    private Object completness;

    private static final long serialVersionUID = 1L;
}