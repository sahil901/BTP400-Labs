package btp400.lab2.usr;

import btp400.lab2.abstractPackage.AbstractTaggable;
import btp400.lab2.abstractPackage.Coat;
import btp400.lab2.abstractPackage.Toy;
import btp400.lab2.items.Category;
import btp400.lab2.items.Size;
import btp400.lab2.misc.Colour;

public class UserObjectTest {

	// can also use instance of 
	// creating coat and toy and grabbing tag or each
    public void testMethod(){
        AbstractTaggable abstractTaggable = new Coat("COLOUR1",new Colour(0,255,255),Size.L,Category.CHILDREN);
        System.out.println(abstractTaggable.getTag());

        abstractTaggable = new Toy("TOY1",0,3);
        System.out.println(abstractTaggable.getTag());

    }
}
