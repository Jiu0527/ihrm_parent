package cn.sh.softline.exception;

import cn.sh.softline.common.entity.ResultCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommonException extends RuntimeException{

    private static final long serialVersionUID = 6070418418848530823L;

    private ResultCode code = ResultCode.SERVER_ERROR;

    public CommonException(ResultCode code){
        super(code.getMessage());
        this.code = code;
    }
}
