import java.util.Iterator;
import java.util.NoSuchElementException;

class PeekingIterator implements Iterator<Integer>{
    private Iterator<Integer> iterator;
    private Integer nextElement;

    public PeekingIterator(Iterator<Integer> iterator){
        this.iterator = iterator;
        advance();
    }
    private void advance(){
        if (iterator.hasNext()){
            nextElement = iterator.next();
        } else{
            nextElement = null;
        }
    }

    public Integer peek(){
        if (nextElement == null){
            throw new NoSuchElementException();
        }

        return nextElement;
    }

    @Override
    public Integer next(){
        if (nextElement == null){
            throw new NoSuchElementException();
        }

        Integer value = nextElement;
        advance();
        return value;
    }

    @Override
    public boolean hasNext(){
        return nextElement != null;
    }
}