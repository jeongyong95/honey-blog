import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import {RegisterFormState} from "../type/RegisterFormState";
import axios from "axios";

const RegisterView = () => {
  const [formState, setFormState] = useState<RegisterFormState>({
    email: "", name: "", password: "", passwordConfirm: "", roleCode: "ROLE_VISITOR"
  })
  const navigate = useNavigate()
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault()
    try {
      axios.post('/v1/users', formState).then(r => console.log(r.status));
      navigate('/')
    } catch (e) {
      console.log(e)
    }
  }

  return (
      <form onSubmit={handleSubmit}>
        <fieldset>
          <legend>회원가입</legend>
          <ul>
            <li className={"mb-3"}>
              <label htmlFor={"name"}>이름</label>
              <input id={"name"}
                     type={"text"}
                     placeholder={"이름을 입력하세요."}
                     value={formState.name}
                     onChange={(event) => {
                       setFormState({...formState, name: event.target.value})
                     }}/>
            </li>
            <li>
              <label htmlFor={"email"}>이메일</label>
              <input id={"email"}
                     type={"email"}
                     placeholder={"이메일을 입력하세요."}
                     value={formState.email}
                     onChange={(event) => {
                       setFormState({...formState, email: event.target.value})
                     }}/>
            </li>
            <li>
              <label htmlFor={"password"}>비밀번호</label>
              <input id={"password"}
                     type={"password"}
                     placeholder={"비밀번호를 입력하세요."}
                     value={formState.password}
                     onChange={(event) => {
                       setFormState({...formState, password: event.target.value})
                     }}/>
            </li>
            <li>
              <label htmlFor={"passwordConfirm"}>비밀번호 확인</label>
              <input id={"passwordConfirm"}
                     type={"password"}
                     placeholder={"비밀번호를 한번 더 입력하세요."}
                     value={formState.passwordConfirm}
                     onChange={(event) => {
                       setFormState({...formState, passwordConfirm: event.target.value})
                     }}/>
            </li>
          </ul>
        </fieldset>
        <input type={"submit"} value={"가입하기"}/>
        <input type={"reset"} value={"취소하기"}/>
      </form>
  )
}

export default RegisterView