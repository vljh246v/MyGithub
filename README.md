# JAVA 8 

## 스트림

### 스트림이란
스트림은 반복자로써 컬렉션(배열 포함)의 요소를 하나씩 참조해서 람다식으로 처리할 수 있는 반복자

#### 자바 8 이전 코드 
```JAVA 
List<String> list = Arrays.asList("김두한", "구마적", "신마적");
Iterator<String> iterator = list.iterator();
while(iterator.hasNext()) {
   String name = iterator.next();
   System.out.println(name);
}
```

#### 자바 8 이후 코드
```JAVA
List<String> list = Arrays.asList("김두한", "구마적", "신마적");
Stream<String> stream = list.stream();
stream.forEach(name -> System.out.println(name));
```

### 스트림 특징
람다식으로 요소 처리 코드를 제공
1. 스트림이 제공하는 대부분의 요소 처리 메소드는 함수적 인터페이스 매개 타입을 가짐
2. 매개변수로 람다식 또는 메소드 참조를 대입할 수 있음

````JAVA
public static void main(String[] args) {
  List<Student> list = Array.asList(
    new Student("김두한",92),
    new Student("구마적",90),
    new Student("신마적",95)
  );
  
  Stream<Student> stream = list.stream();
  stream.forEach(s -> {
    String name = s.getName();
    int score = s.getScore();
    System.out.println(name + "-" + score);
  });
}
````
