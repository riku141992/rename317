package rs2;


import rs2.model.entity.Entity;

public class ObjectOnTile extends Entity {
                     //ObjectAnimation
    public Model getRotatedModel()
    {
        int anim = -1;
        if(sequence != null)
        {
            int k = Client.currentTime - delay;
            if(k > 100 && sequence.frameStep > 0)
                k = 100;
            while(k > sequence.getFrameLength(frame))
            {
                k -= sequence.getFrameLength(frame);
                frame++;
                if(frame < sequence.frameCount)
                    continue;
                frame -= sequence.frameStep;
                if(frame >= 0 && frame < sequence.frameCount)
                    continue;
                sequence = null;
                break;
            }
            delay = Client.currentTime - k;
            if(sequence != null)
                anim = sequence.frame2IDS[frame];
        }
        ObjectDef objectDef;
        if(anIntArray1600 != null)
            objectDef = method457();
        else
            objectDef = ObjectDef.forID(objectID);
        if(objectDef == null)
        {
            return null;
        } else
        {
            return objectDef.renderObject(anInt1611, anInt1612, anInt1603, anInt1604, anInt1605, anInt1606, anim);
        }
    }

    private ObjectDef method457()
    {
        int i = -1;
        if(anInt1601 != -1)
        {
            VarBit varBit = VarBit.cache[anInt1601];
            int k = varBit.variable;
            int l = varBit.leastSignificantBit;
            int i1 = varBit.mostSignificantBit;
            int j1 = StaticLogic.BITFIELD_MAX_VALUE[i1 - l];
            if (clientInstance == null)
                i = 0;
            else
                i = clientInstance.session_variables[k] >> l & j1;
        } else
        if(anInt1602 != -1)
            if (clientInstance == null)
                i = 0;
            else
                i = clientInstance.session_variables[anInt1602];
        if(i < 0 || i >= anIntArray1600.length || anIntArray1600[i] == -1)
            return null;
        else
            return ObjectDef.forID(anIntArray1600[i]);
    }

    public ObjectOnTile(int objectID, int j, int k, int l, int i1, int j1,
                         int k1, int animationID, boolean randomize)
    {
        this.objectID = objectID;
        anInt1611 = k;
        anInt1612 = j;
        anInt1603 = j1;
        anInt1604 = l;
        anInt1605 = i1;
        anInt1606 = k1;
        if(animationID != -1)
        {
            sequence = Sequence.anims[animationID];
            frame = 0;
            delay = Client.currentTime;
            if(randomize && sequence.frameStep != -1)
            {
                frame = (int)(Math.random() * (double) sequence.frameCount);
                delay -= (int)(Math.random() * (double) sequence.getFrameLength(frame));
            }
        }
        ObjectDef class46 = ObjectDef.forID(this.objectID);
        anInt1601 = class46.configId_1;
        anInt1602 = class46.configID;
        anIntArray1600 = class46.configObjectIDs;
    }

    private int frame;
    private final int[] anIntArray1600;
    private final int anInt1601;
    private final int anInt1602;
    private final int anInt1603;
    private final int anInt1604;
    private final int anInt1605;
    private final int anInt1606;
    private Sequence sequence;
    private int delay;
    public static Client clientInstance;
    private final int objectID;
    private final int anInt1611;
    private final int anInt1612;
}
