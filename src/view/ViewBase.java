package view;

public class ViewBase<T> implements IView<T> {

    private T myNode;
    
    public ViewBase(T node) {
        myNode = node;
    }
    
    @Override
    public T getView() {
        return myNode;
    }
}
