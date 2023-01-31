package myProject.validator;

public interface Validator <E,R>{
    R isValidComment(E e);

    R isValidUser(E e);
}
