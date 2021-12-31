package classes.nested;

public class Button implements View {
    @Override
    public State getCurrentState() {
        return new ButtonState();
    }
    @Override
    public void restoreState(State state) { /*...*/ }

    public class ButtonState implements State { /*...*/ }
}
