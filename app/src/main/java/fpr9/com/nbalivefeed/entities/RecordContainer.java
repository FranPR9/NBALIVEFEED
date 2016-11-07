package fpr9.com.nbalivefeed.entities;

/**
 * Created by FranciscoPR on 07/11/16.
 */
public class RecordContainer {
    private String id;
    private Record record;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
