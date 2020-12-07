package com.laurier.joelucy.CP670project.BD;

public class LogItem {
    public long id;
    public String content;
    public long itemType;   // 1 is goal 2 is mood
    public String CreateOn;
    public LogItem(){}
    public LogItem(long itemId, String itemContent, long type)
    {
        id = itemId;
        content = itemContent;
        itemType = type;
    }

    public LogItem(long itemId, String itemContent, long type, String createOn)
    {
        id = itemId;
        content = itemContent;
        itemType = type;
        CreateOn = createOn;
    }

    public String toString() {return content;}
}
