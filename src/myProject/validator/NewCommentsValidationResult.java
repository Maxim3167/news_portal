package myProject.validator;

public class NewCommentsValidationResult {
    private boolean violation = false;

    public void setViolation(boolean violation) {
        this.violation = violation;
    }

    public boolean isValid(){
        return violation;
    }
}
