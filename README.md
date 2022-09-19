# mini2
## 스프링부트, JPA와 리액트를 사용한 2차 미니프로젝트

# 개발목적

- SpringBoot로 Backend구현 / React로 Frontend구현
- RestApi를 이용하여 서버(Springboot)와 클라이언트(React)를연결

------------------------------------------------------------------------------------------------------------------------------------------

# 개발환경
>front-end
- JavaScript
- React

>back-end
- Java
- springBoot
- JPA
- oracle DB
- lombok 
- tomcat 





------------------------------------------------------------------------------------------------------------------------------------------

# DB 모델링
<div>
  <img src="https://user-images.githubusercontent.com/105841315/190939702-0500c946-d331-4a35-9071-2d9eaf2d1baa.png">
</div>




------------------------------------------------------------------------------------------------------------------------------------------

# 기능별 주요 로직 SpringBoot 
>  자유게시판 글등록&상세보기&글삭제 
- 글등록&상세보기&글삭제 <br>
### register.jsp: 자유게시판 FreeRepository
~~~java
@Repository
public interface FreeRepojitory extends JpaRepository<Free, Long>{

	

	
}

~~~

### FreeController : Restapi 형식으로 코딩
 ~~~java
 @RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class FreeController {

	@Autowired
	private FreeService freeService;
	@PostMapping("/insert")//글쓰기
	public ResponseEntity<?> save(@RequestBody Free free){
		return new ResponseEntity<>(freeService.save(free), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/view/{id}")//상세보기
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		return new ResponseEntity<>(freeService.select(id),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{id}")//글삭제
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		freeService.delete(id);
		return new ResponseEntity<>(freeService.delete(id),HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")//글수정
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Free free){
		freeService.update(id, free);
		return new ResponseEntity<>(freeService.update(id, free),HttpStatus.OK);
	}
	
 ~~~
 
### Restapi 글등록 테스트 

![image](https://user-images.githubusercontent.com/101411257/190948770-fe3a5eb9-04d8-4d93-a03a-aafbe439f252.png)


<br>

### Restapi 삭제 테스트 
![image](https://user-images.githubusercontent.com/101411257/190948855-b9ed8c86-76b2-4917-a869-3c74e86ee2d3.png)


<br>


# 기능별 주요 로직 React 
>  자유게시판 글등록&글상세&글삭제
- 글등록<br>
### insert.js: 글등록 구현 페이지
~~~JavaScript
const Insert = (props) => {
    const[free, setFree] = useState({
        title:"",
        writer:"",
        content:"",
    });


const changeValue = (e) =>{
    setFree({
        ...free,
        [e.target.name] : e.target.value
    });
}

const submitFree = (e) => {
    e.preventDefault();

    fetch('http://localhost:9008/insert',{
        method: 'POST',
        headers:{
            'Content-Type':'application/json; charset=utf-8',
        },
        body: JSON.stringify(free),
    })
    .then((res)=>{
        if(res.status === 201){
            alert('글 등록 완료');
            return res.json();
        }else{
            return null;
        }
    })
    .then((res) => {
        if(res !== null){
            props.history.push('/viewpage');
        }else{
            alert('글 등록에 실패하셨습니다.');
        }
    });
};

return( 
    <>
    <form onSubmit={submitFree}>
     <h1>"자유게시판"</h1>
     <br/>
     <br/>

    <h3 id="form">제목</h3>
        <input
          
          type="text"
          name="title"
          placeholder="제목을 입력해 주세요"
          onChange={changeValue}
          
          
        />
        
        <br />
        <h3 id="form">작성자</h3>
        <input
        
          type="text"
          name="writer"
          placeholder="작성자"
          onChange={changeValue}
          />
        <br />
        <br/>
        <h3 id="form">내용</h3>
        <textarea
        
          rows="30"
          cols="50"
          name="content"
          
          onChange={changeValue}
        ></textarea>
      <br/>
      <button type="submit" className="w-btn-outline w-btn-indigo-outline">
        글등록
      </button>
      </form>
  </>
);
};
export default Insert;
   
~~~

### view.js: 글상세&글삭제 페이지
~~~JavaScript
const View = (props) => {
    console.log('view',props);
    const id = props.match.params.id;

    const[free, setFree] = useState({
        id:'',
        title:'',
        writer:'',
        content:'',
    });

    useEffect(()=>{
        fetch('http://localhost:9008/view/'+id)
        .then((res) => res.json())
        .then((res)=>{
            setFree(res);
        });
    },[]);

    const deleteFree = () => {
        fetch('http://localhost:9008/delete/'+id,{
            method: 'DELETE',
        })
        .then((res) => res.text())
        .then((res) =>{
            if (res === 'ok'){
                alert('삭제성공')
                props.history.push('/');
            }else{
                alert('삭제성공')
                props.history.push('/');
            }
        });
    };

    const updateFree = () =>{
        props.history.push('/update/' + id);
    };

    return(
        <div>
            
            <h1>자유게시판</h1>
            <br/>
                {/* <table>
                    <tr>
                    <td>작성자</td>
                    <td>{free.writer}</td>
                    </tr>
               
                    <tr>
                    <td>제목</td>
                    <td>{free.title}</td>
                    </tr>
                
                    <tr>
                    <td>내용</td>
                    <td>{free.content}</td>
                    </tr>
                </table> */}
                
                {/* <h3>{free.writer}</h3>
                <h3>{free.title}</h3>
                <h3>{free.content}</h3> */}

<h3 id="form">제목</h3>
        <input
          className="inputField"
          type="text"
          name="title"
          value={free.title}
          readOnly
        />
        
        <br />
        <h3 id="form">작성자</h3>
        <input
        className="inputField"
          type="text"
          name="writer"
          value={free.writer}
          readOnly
          />
        <br />
        <h3 id="form">내용</h3>
        <textarea
        className="inputField"
          rows="30"
          cols="50"
          name="content"
          value={free.content}
          readOnly
        ></textarea>
      <br/>
                <button onClick={updateFree} className="w-btn-outline w-btn-green-outline">
                    수정
                </button>

            {'   '}

                <button onClick={deleteFree} className="w-btn-outline w-btn-green2-outline">
                    삭제
                </button>
        </div>
    );
};

export default View;
~~~



 

------------------------------------------------------------------------------------------------------------------------------------------

# 스프링부트와 리액트로 진행하며 느낀점
- jsp model2 mvc에 비해 의존성 주입을 활용한 어노테이션으로 객체 자동 생성이 편하다.
- jsp model2 mvc에서 요청한 URL을 잘라서 String 변수command 를 통해  요청을 해결하는 것보다 @requestmapping 혹은
@postmapping, @getmapping 한방으로 끝나는 요청 분기 나누는게 편하다.
- jsp model2 mvc의 preparedStatment 에 비해 월등히 편한 mybatis
- junit의 단위테스트의 편리함. 꼭 실행화면에서 안해도 된다. was를 사용하지 않아도 되서 실행시간을 엄청 절감 할 수 있다.
일반 main메소드에서 테스트를 한다고하면 해당 클래스를 만들고 메소드를 호출해야하는 번거로움 있다.
그냥 junit 테스트코드에서 단위테스트하면 여러 메소드가 바로 이루어지고 junit탭에 정리가 되어서 나와 에러가 뜨면 바로바로 잡을수 있어서 좋았음.
기능별로 하나의 단위테스트 모듈을 만들어 두면 두고두고 써먹을 수 있을 것 같다.
- 권한에 따른 흐름제어를 내가 1부터 코딩 하는것이 아니고 spring security를 통해 쉽게 제어할 수 있다.
- lombok을 통해 setter, getter, tostring 작성을 생략 가능해 편하다.


------------------------------------------------------------------------------------------------------------------------------------------
