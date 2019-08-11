package utils.excel;

public enum ExportTagEnum {

    EXPORTDISKTAG(1),

    EXPORTINTERNET(2);


    private int tag;

    private ExportTagEnum(int tag){
        this.tag = tag;
    }
}
