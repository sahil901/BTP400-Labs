package btp400.lab2.usr;
import btp400.lab2.items.Category;
import btp400.lab2.tag.CoatImpl;
import btp400.lab2.tag.Taggable;
import btp400.lab2.tag.ToyImpl;

import java.util.ArrayList;
import java.util.List;

public class UserApp {
    private List<Object> list;

    // setting a empty array list
    public UserApp() {
        this.list = new ArrayList<>();
    }

    // taking in array list and mem var 
    public UserApp(List<Object> list) {
        this.list = list;
    }

    // adding an object to the list 
    public void addToList(Object o){
        list.add(o);
    }

    // returning the size of the list 
    public int sizeOfList(){
        return list.size();
    }


    // grabs the appropirate childern (instance of the correct obj)
    public List<Object> getChildrenAppropriateObject(){
        List<Object> childrenObjects = new ArrayList<>();

        for (Object o:list) {
            if(o instanceof ToyImpl){
                childrenObjects.add(o);
            }else if( o instanceof CoatImpl){
                if(((CoatImpl) o).getCategory().equals(Category.CHILDREN)){
                    childrenObjects.add(o);
                }
            }
        }
        return childrenObjects;
    }

    // get all the tags of everything insifde the array list
    public List<String> getAllTags(){
        List<String> tags = new ArrayList<>();
        for (Object o:list) {
            tags.add(((Taggable)o).getTag());
        }
        return tags;
    }

}
