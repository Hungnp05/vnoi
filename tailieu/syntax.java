package tailieu;

public class syntax {
    Dưới đây là tổng hợp **các cú pháp Java quan trọng** được sử dụng thường xuyên và **phù hợp với các cuộc thi hackathon**, đặc biệt là khi bạn cần giải quyết các bài toán thuật toán và công nghệ nhanh chóng, hiệu quả:

---

## 💡 1. **Cấu trúc cơ bản**

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Hackathon!");
    }
}
```

---

## 🔢 2. **Biến & Kiểu dữ liệu**

```java
int x = 10;
double pi = 3.14;
boolean isValid = true;
char ch = 'A';
String name = "Hackathon";
```

---

## 🔁 3. **Vòng lặp**

```java
// For
for (int i = 0; i < 10; i++) {
    System.out.print(i + " ");
}

// While
int i = 0;
while (i < 10) {
    System.out.print(i + " ");
    i++;
}
```

---

## 🔀 4. **Rẽ nhánh**

```java
if (x > 0) {
    System.out.println("Positive");
} else if (x < 0) {
    System.out.println("Negative");
} else {
    System.out.println("Zero");
}
```

---

## 📦 5. **Array & ArrayList**

```java
int[] arr = {1, 2, 3, 4};
for (int num : arr) System.out.println(num);

import java.util.*;
ArrayList<Integer> list = new ArrayList<>();
list.add(5);
list.get(0); // Truy cập phần tử
```

---

## 🧠 6. **HashMap & Set** – rất hữu ích cho các bài toán map, đếm, v.v.

```java
HashMap<String, Integer> map = new HashMap<>();
map.put("A", 1);
map.get("A"); // -> 1

HashSet<Integer> set = new HashSet<>();
set.add(10);
set.contains(10); // true
```

---

## 🧮 7. **Hàm (Function)**

```java
public static int sum(int a, int b) {
    return a + b;
}
```

---

## 🧵 8. **Sort & Collections**

```java
int[] arr = {5, 2, 4};
Arrays.sort(arr);

ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 4));
Collections.sort(list);

// Sort giảm dần
list.sort((a, b) -> b - a);
```

---

## 🌳 9. **Lớp & Đối tượng (OOP)**

```java
class Point {
    int x, y;
    Point(int x, int y) { this.x = x; this.y = y; }
}

Point p = new Point(1, 2);
```

---

## ⏱️ 10. **Đo thời gian chạy**

```java
long start = System.currentTimeMillis();
// ... chạy thuật toán
long end = System.currentTimeMillis();
System.out.println("Time: " + (end - start) + "ms");
```

---

## ⚙️ 11. **Priority Queue (Min/Max Heap)**

```java
PriorityQueue<Integer> pq = new PriorityQueue<>(); // Min heap
pq.add(3); pq.add(1); pq.add(2);
System.out.println(pq.poll()); // -> 1

// Max heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```

---

## 🔂 12. **DFS/BFS dùng Stack/Queue**

```java
Stack<Integer> stack = new Stack<>();
Queue<Integer> queue = new LinkedList<>();
```

---

## 💼 13. **Scanner (nhập dữ liệu)**

```java
Scanner sc = new Scanner(System.in);
int n = sc.nextInt();
String s = sc.nextLine();
```

---

## 📁 14. **Làm việc với file (đọc)**

```java
BufferedReader br = new BufferedReader(new FileReader("input.txt"));
String line;
while ((line = br.readLine()) != null) {
    System.out.println(line);
}
```

---

## 🎯 Gợi ý: Để hackathon hiệu quả:

* Dùng **`HashMap/Set`** để xử lý lookup nhanh.
* Dùng **`PriorityQueue`** cho các bài toán Dijkstra, Greedy.
* Dùng **`ArrayList`/`LinkedList`** cho thao tác linh hoạt trên danh sách.
* Biết cách viết **class tùy chỉnh với Comparable** để sort theo quy tắc riêng.

---

Bạn muốn mình tạo một **template Java hackathon chuẩn hóa** có sẵn tất cả các cú pháp trên (để bạn copy vào IDE dùng nhanh)?

}
