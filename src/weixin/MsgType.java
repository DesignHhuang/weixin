package weixin;


public enum MsgType {
    TEXT(0), VOICE(3), IMAGE(2), VIDEO(4), IMAGE_TEXT(10);
    private int type;

    private MsgType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
