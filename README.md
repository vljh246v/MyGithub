## 1. 스트림

### 스트림이란
스트림은 반복자로써 컬렉션(배열 포함)의 요소를 하나씩 참조해서 람다식으로 처리할 수 있는 반복자

#### 자바 8 이전 코드 
````JAVA 
List<String> list = Arrays.asList("김두한", "구마적", "신마적");
Iterator<String> iterator = list.iterator();
while(iterator.hasNext()) {
   String name = iterator.next();
   System.out.println(name);
}
````
````
[실행 결과]
김두한 구마적 신마적 
````

#### 자바 8 이후 코드
````JAVA
List<String> list = Arrays.asList("김두한", "구마적", "신마적");
Stream<String> stream = list.stream();
stream.forEach(name -> System.out.println(name));
````
````
[실행 결과]
김두한 구마적 신마적 
````

### 스트림 특징
1. 람다식으로 요소 처리 코드를 제공
- 스트림이 제공하는 대부분의 요소 처리 메소드는 함수적 인터페이스 매개 타입을 가짐
- 매개변수로 람다식 또는 메소드 참조를 대입할 수 있음

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
````
[실행 결과]
김두한-92
구마적-90
신마적-95
````

2. 내부 반복자를 사용하므로 병렬 처리가 쉽다.
- 외부 반복자(external iterator)
  > 개발자가 코드로 직접 컬렉션 요소를 반복해서 요청하고 가져오는 코드 패턴
- 내부 반복자(internal iterator)
  > 컬렉션 내부에서 요소들을 반복시키고 개발자는 요소당 처리해야할 코드만 제공하는 코드 패턴
- 내부 반복자의 이점
  > 개발자는 요소 처리 코드에만 집중 
  > 멀티 코어 CPU를 최대한 활용하기 위해 요소들을 분배시켜 병렬 처리 작업을 할 수 있음
- 병렬(parallel)처리
  > 한 가지 작업을 서브 작업으로 나누고, 서브 작업들을 분리된 스레드에서 병렬적으로 처리한 후, 서브 작업들의 결과들을 최종 결합하는 방법
  > **자바는 ForkJoinPool 프레임워크를 이용해서 병렬 처리를 함** 
- 병렬 처리 코드 예

````JAVA
public class ParallelEx {
  public static void main(String[] args) {
    List<String> list = Arrays.asList("김두한","구마적","신마적","람다식","병렬처리");

    Stream<String> stream = list.stream();
    stream.forEach(ParallelEx::print);

    Stream<String> parallelStream = list.parallelStream();
    parallelStream.forEach(ParallelEx::print);
  }

  public static void print(String string) {
    System.out.println(string + " : " + Thread.currentThread().getName());
  }
}
````
````
[실행 결과]
김두한 : main
구마적 : main
신마적 : main
람다식 : main
병렬처리 : main
신마적 : main
람다식 : ForkJoinPool.commonPool-worker-2
병렬처리 : main
구마적 : ForkJoinPool.commonPool-worker-1
김두한 : ForkJoinPool.commonPool-worker-2
````

### 병렬 처리 (Parallel Operation)
1. 멀티 코어 CPU 환경에서 하나의 작업을 분할해서 각각의 코어가 병렬적으로 처리
- 병렬 처리의 목적 : 작업 처리 시간을 줄임
- 자바8부터 병렬 스트림을 제공하므로 컬렉션(배열)의 전체 요소 처리 시간을 줄여줌

2. 동시성(Concurrency)과 병렬성(Parallelism)
- 동시성 : 멀티 스레드 환경에서 스레드가 번갈아 가며 실행하는 성징(싱글 코어 CPU)
- 병렬성 : 멀티 스레드 환경에서 코어들이 스레드를 병렬적으로 실행하는 성질(멀티 코어 CPU)

3. 병렬성(Parallelism) 구분
- 데이터 병렬성
  > 데이터 병렬성은 한 작업 내에 있는 전체 데이터를 쪼개어 서브 데이터들로 만들고, 이 서브 데이터들을 
    병렬 처리해서 작업을 빨리 끝내는 것
- 작업 병렬성
  > 서로 다른 작업을 병렬 처리하는 것
  > 작업 병렬성의 대표적인 예는 웹서버(Web Server)로 웹서버는 각각의 브라우저에서 요청한 내용을 
    개별 스레드에서 병렬로 처리

4. 병렬 스트림은 데이터 병렬성을 구현한 것
- 멀티 코어의 수만큼 대용량 요소를 서브 요소들로 나누고, 각각의 서브 요소들을 분리된 스레드에서 
  병렬 처리 시킴
- 예를 들어 쿼드 코어 CPU일 경우, 4개의 서브 요소들로 나누어 4개의 스레드가 각각의 서브 요소들을 병렬 처리함
- 병렬 스트림은 내부적으로 포크 조인(ForkJoin) 프레임워크를 이용


### 포크 조인(ForkJoin) 프레임워크
* **포크 조인 프레임워크 동작 방식**
- 포크 단계
  > 데이터를 서브 데이터로 반복적으로 분리
  > 서브 데이터를 멀티 코어에서 병렬로 처리
- 조인 단계

 
