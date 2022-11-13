package MyLibrary.Management;

public interface ManagementCrud <T> {
    void insert(T item);
    void update(T item);
    T getById(int id);
    void delete(int id);
}
