package api.payload;

public class ObjectListPojo {
    String name;
    ObjectDataPojo data;
    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectDataPojo getData() {
        return data;
    }

    public void setData(ObjectDataPojo data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ObjectListPojo{" +
                "name='" + name + '\'' +
                ", data=" + data +
                ", id=" + id +
                '}';
    }


}
