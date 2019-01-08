package DesignPatterns.BehaviouralPattern;

interface Iterator{
    public boolean hasNext();
    public Object next();
}
//--------------------------------------------------
interface Container{
    public Iterator getIterator();
}
//--------------------------------------------------
class NameRepository implements Container{
    public String names[] = {"Robert", " John", "Julie", "Lora"};

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator{
        int index;

        @Override
        public boolean hasNext() {
            if(index< names.length){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}

public class IteratorPattern {
    public static void main(String[] args) {
        NameRepository nameRepository = new NameRepository();

        for(Iterator iter = nameRepository.getIterator(); iter.hasNext();){
            String name = (String)iter.next();
            System.out.println("Name: "+name);
        }
    }
}
