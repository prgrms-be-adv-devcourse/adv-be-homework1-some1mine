package site.thedeny1106.homework.common;

public record ResponseEntity<T> (
        int status, Object data, long count
){}
