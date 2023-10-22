
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

function RegisterCreate() {

  // REDIRECT
  const navigate = useNavigate();

  // STATE
  const [registerNickName, setRegisterNickName] = useState();
  const [registerName, setRegisterName] = useState();
  const [registerSurname, setRegisterSurname] = useState();
  const [registerEmail, setRegisterEmail] = useState();
  const [registerPassword, setRegisterPassword] = useState();
  const [registerIsPassive, setRegisterIsPassive] = useState(false);

  // STATE ERROR, MULTIPLEREQUEST, READ
  const [error, setError] = useState();
  const [multipleRequest, setMultipleRequest] = useState();
  const [isRead, setIsRead] = useState();

  // USE EFFECT
  useEffect(() => {
    setError(undefined);
  }, [])

  // FUNCTION

  // OnChange
  const registerOnChange = (event) => {
    const { name, value } = event.target;
    setRegisterNickName(value);
    setRegisterName(value);
    setRegisterSurname(value);
    setRegisterEmail(value);
    setRegisterPassword(value);
    setRegisterIsPassive(value);
  }

  // registerCreateSubmit
  const registerCreateSubmit=(event)=>{

  }

  // onSubmitSearch
 const onSubmitForm=(e)=> {
    e.preventDefault();
  }

  // RETURN
  return (
    <React.Fragment>
      <h1>Register Create</h1>
      <form onSubmit={onSubmitForm}>
      {/* <form onSubmit="event.preventDefault()"> */}
        <div className="d-grid gap-4">
          {/* NICKNAME */}
          <div className="form-group"><label htmlFor="">NickName</label>
            <input
              type="text"
              className='form-control'
              id="registerNickName"
              name="registerNickName"
              placeholder='registerNickName'
              autoFocus={true}
              required={true}
              onChange={registerOnChange} />
          </div>

          {/* registerName */}
          <div className="form-group"><label htmlFor="">registerName</label>
            <input
              type="text"
              className='form-control'
              id="registerName"
              name="registerName"
              placeholder='registerName'
              autoFocus={false}
              required={true}
              onChange={registerOnChange} />
          </div>


          {/* registerSurname */}
          <div className="form-group"><label htmlFor="">registerSurname</label>
            <input
              type="text"
              className='form-control'
              id="registerSurname"
              name="registerSurname"
              placeholder='registerSurname'
              autoFocus={false}
              required={true}
              onChange={registerOnChange} />
          </div>


          {/* registerEmail */}
          <div className="form-group"><label htmlFor="">registerEmail</label>
            <input
              type="email"
              className='form-control'
              id="registerEmail"
              name="registerEmail"
              placeholder='registerEmail'
              autoFocus={false}
              required={true}
              onChange={registerOnChange} />
          </div>


          {/* registerPassword */}
          <div className="form-group"><label htmlFor="">registerPassword</label>
            <input
              type="password"
              className='form-control'
              id="registerPassword"
              name="registerPassword"
              placeholder='registerPassword'
              autoFocus={false}
              required={true}
              onChange={registerOnChange} />
          </div>
        </div>

        <input  type="checkbox" name="" id="" /> Okudunuz mu ? <br />
        <button type='reset' className="btn btn-danger mt-2 me-2">Temizle</button>
        <button type='submit' onClick={registerCreateSubmit} className="btn btn-primary mt-2 me-2" disabled={true}>Gönder</button>
      </form>
    </React.Fragment>
  )
}

export default RegisterCreate