package org.plus.agent.collector.collection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.plus.agent.collector.init.NotProguard;

/**
 * 错误信息统计
 *
 * @author mobingsen
 */
@Data
@EqualsAndHashCode
@ToString
@NotProguard
public class ErrorLog {
    private String logType;
    private String statck;
    private String errorMsg;
    private String errorType;
    private String ip;
    private String keyId;
    private Long createTime;
}
