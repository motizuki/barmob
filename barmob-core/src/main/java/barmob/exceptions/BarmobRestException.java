package barmob.exceptions;

/**
 * Created by gustavokm90 on 3/20/15.
 */
public class BarmobRestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public BarmobRestException(String errCode, String errMsg) {
        super(errCode+errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

}