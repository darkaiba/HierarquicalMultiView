
package websensorhtml;

public class Node {
    private int id;
    private String name;
    private int group;

    public Node() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "\n{id:" +id+ ", 'label':'" + name+"', 'group':" + group + "}";
    }
    
    
}
