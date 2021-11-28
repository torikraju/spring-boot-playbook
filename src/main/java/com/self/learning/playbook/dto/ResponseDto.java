package com.self.learning.playbook.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.self.learning.playbook.constants.Status;
import com.self.learning.playbook.util.CommonUtils;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class ResponseDto  {
    private String requestId;
    private Status status;
    private String description;
    @JsonIgnore
    private Date apiRequestTime;
    @JsonIgnore
    private Date apiResponseTime;
    private String nodeIp;
    private String errorDetails;

    public ResponseDto() {
        this.nodeIp = CommonUtils.hostIp;
    }
}
