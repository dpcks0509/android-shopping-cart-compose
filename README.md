# android-shopping-cart-compose

## 🛠 사용 기술
- Language : Kotlin
- Architecture: MVI, Clean Architecture
- Jetpack : Compose, Navigation
- Asynchronous : Coroutines, Flow
- DI : Hilt

</br>

## 1단계 - 장바구니(상품 목록)
<p align="center">
  <img src="https://github.com/user-attachments/assets/524995aa-f8d0-4a93-a657-2dc5920f3ebb" width="30%" />
</p>

### 기능 요구 사항

- [x] 상품 목록 화면을 구현한다.

### 프로그래밍 요구 사항

- [x] Repository, DataSource 구현 리소스를 최대한 줄이고, 컴포즈 학습에 집중하기 위한 수단으로 활용한다.
- [x] 상품 목록 화면을 구현할 때 Lazy 컴포넌트를 활용한다.
- [x] 컴포저블 함수가 너무 많은 일을 하지 않도록 분리하기 위해 노력해 본다.
    - [x] 의미있는 단위의 함수를 모아 별도의 파일로 분리해본다.

</br>

## 2단계 - 장바구니(상품 상세)
<p align="center">
  <img src="https://github.com/user-attachments/assets/e0e630b4-7c3f-425b-9492-20c377b9cd1f" width="30%" />
  <img src="https://github.com/user-attachments/assets/cbacaeea-2ea3-4795-809f-5d02d93f83e1" width="30%" />
</p>

### 기능 요구 사항

- [x] 상품 상세 화면을 구현한다.
    - [x] 상품 목록에서 상품을 누르면 상품 상세 화면으로 이동한다.
    - [x] 뒤로 가기 버튼이나 아이콘을 누르면 직전 화면으로 돌아간다.
- [x] 장바구니 화면의 빈 껍데기를 연결한다.
    - [x] 상품 목록에서 장바구니 아이콘을 누르면 장바구니 화면으로 이동한다.
    - [x] 상품 상세에서 장바구니 담기 버튼을 누르면 장바구니 화면으로 이동한다.
    - [x] 뒤로 가기 버튼이나 아이콘을 누르면 직전 화면으로 돌아간다.
    - [x] 장바구니에 실제로 상품이 담기는 기능은 이 단계에서 고려하지 않는다.

### 프로그래밍 요구 사항

- [x] 장바구니에 실제로 상품이 담기는 기능은 이 단계에서 고려하지 않는다.
- [x] 컴포저블 함수가 너무 많은 일을 하지 않도록 분리하기 위해 노력해 본다.
    - [x] 의미있는 단위의 함수를 모아 별도의 파일로 분리해본다.

</br>

## 3단계 - 장바구니(담기)
<p align="center">
  <img src="https://github.com/user-attachments/assets/990be440-2b6e-48d8-ab82-797af38d86ef" width="30%" />
</p>

### 기능 요구 사항

- [x] 상품을 장바구니에 담는 기능을 구현한다.
- [x] 장바구니 화면을 구현한다.
    - [x] 담긴 상품의 수량을 조절할 수 있어야 한다.
    - [x] 수량을 1보다 작게 하면 장바구니에서 상품이 제거된다
    - [x] 담긴 상품 가격의 총합이 주문하기 버튼에 표시된다.

### 프로그래밍 요구 사항

- [x] 상품을 주문하는 기능에 대해서는 구현하지 않아도 된다.
- [x] 장바구니 화면에 대한 테스트 코드를 작성한다.

</br>

## 4단계 - 장바구니(수량)
<p align="center">
  <img src="https://github.com/user-attachments/assets/26811391-5cd5-4dbc-afee-e72d5f90e3ff" width="30%" />
  <img src="https://github.com/user-attachments/assets/cb1e26ea-4b6c-4958-aed6-9eb0aee86299" width="30%" />
  <img src="https://github.com/user-attachments/assets/39cb3cd5-d246-4c09-b5ee-075a764eb2d2" width="30%" />
</p>


### 기능 요구 사항

- [x] 상품 목록에서 장바구니에 담을/담긴 상품의 수량을 조절할 수 있다.
    - [x] '+' 아이콘을 누르면 장바구니에 상품이 추가됨과 동시에 수량 조절 버튼이 노출된다.
- [x] 상품 목록의 상품 수가 변화하면 장바구니에도 반영되어야 한다. (B마트 UX 참고)
- [x] 장바구니의 상품 수가 변화하면 상품 목록에도 반영되어야 한다. (B마트 UX 참고)

### 프로그래밍 요구 사항

- [x] 반복되는 뷰(상품 수량 조절)를 재사용할 수 있는 방법을 고민해 본다.
- [x] 3단계에서 작성된 장바구니 화면 테스트가 실패하면 안 된다.
