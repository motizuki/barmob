package barmob.templates;

/**
 * Created by gustavokm90 on 3/20/15.
 */
public class ErrorTemplate {

    private String errCode;
    private String errMsg;

    public ErrorTemplate(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

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
}
