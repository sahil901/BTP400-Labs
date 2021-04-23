package btp400.lab2.usr;

import btp400.lab2.items.Category;
import btp400.lab2.items.Size;
import btp400.lab2.misc.Colour;
import btp400.lab2.tag.CoatImpl;
import btp400.lab2.tag.ToyImpl;

import java.util.List;

import static junit.framework.Assert.assertEquals;

class UserAppTest {

	// enssures that things are getting added ot the lsit 
    @org.junit.jupiter.api.Test
    void addToList() {
        UserApp userApp = new UserApp();
        userApp.addToList(new ToyImpl("ABC",6,9));
        userApp.addToList(new ToyImpl("XYZ",6,9));
        userApp.addToList(new CoatImpl("COLOUR1",new Colour(0,255,255),Size.L,Category.CHILDREN));
        userApp.addToList(new CoatImpl("COLOUR2",new Colour(255,0,255),Size.L,Category.ADULT));

        assertEquals( userApp.sizeOfList(),4) ;

    }

    // checks if it has the correct child object
    @org.junit.jupiter.api.Test
    void getChildrenAppropriateObject() {
        UserApp userApp = new UserApp();
        userApp.addToList(new ToyImpl("ABC",6,9));
        userApp.addToList(new CoatImpl("COLOUR2",new Colour(255,0,255),Size.L,Category.ADULT));

        List<Object> childObj = userApp.getChildrenAppropriateObject();
        assertEquals( ((ToyImpl)childObj.get(0)),new ToyImpl("ABC",6,9));
    }

    // ensures that all tags are being grabbed correctly
    @org.junit.jupiter.api.Test
    void getAllTags() {
        UserApp userApp = new UserApp();
        userApp.addToList(new ToyImpl("ABC",6,9));
        userApp.addToList(new CoatImpl("COLOUR2",new Colour(255,0,255),Size.L,Category.ADULT));

        List<String> tags = userApp.getAllTags();
        assertEquals( tags.get(0),"ABC.6.9");
        assertEquals(tags.get(1),"COLOUR2.255.0.255.L.ADULT");
    }


}