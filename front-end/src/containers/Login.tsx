import React, {useState} from "react";
import {useNavigate} from 'react-router-dom'; // 설치한 패키지
import axios from "axios";
import {LoginFormState} from "./type/LoginFormState";

const Login = () => {
  const [formState, setFormState] = useState<LoginFormState>(
      {
        email: '' as const,
        password: '' as const,
      }
  )
  const navigate = useNavigate();

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault()
    try {
      const response = await axios.post('/v1/auth/login', formState)
      localStorage.setItem('auth_tokens', JSON.stringify(response.data))
      navigate('/')
    } catch (e) {
      console.log(e)
    }
  }

  // @ts-ignore
  return (
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor={'email'}>Email:</label>
          <input type={"email"}
                 id={'email'}
                 value={formState.email}
                 onChange={(event) => setFormState({...formState, email: event.target.value})}/>
        </div>
        <div>
          <label htmlFor={'password'}>Password:</label>
          <input type={"password"}
                 id={"password"}
                 value={formState.password}
                 onChange={(event) => setFormState({...formState, password: event.target.value})}/>
        </div>
        <button type={"submit"}>Login</button>
      </form>
  )
}

export default Login