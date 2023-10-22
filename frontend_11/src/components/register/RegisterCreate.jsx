
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

function RegisterCreate() {

  // REDIRECT
  const navigate = useNavigate();

  // STATE
  const [registerNickName, setRegisterNickName] = useState('');
  const [registerName, setRegisterName] = useState('');
  const [registerSurname, setRegisterSurname] = useState('');
  const [registerEmail, setRegisterEmail] = useState('');
  const [registerPassword, setRegisterPassword] = useState('');
  const [registerIsPassive, setRegisterIsPassive] = useState(false);

  // STATE ERROR, MULTIPLEREQUEST, READ
  const [error, setError] = useState(undefined);
  const [multipleRequest, setMultipleRequest] = useState(false);
  const [isRead, setIsRead] = useState(false);

  // USE EFFECT
  useEffect(() => {
    setError(undefined);
  }, [])

  // FUNCTION

  // onSubmitSearch
  const onSubmitForm = (e) => {
    e.preventDefault();
  }

  // Read On Change
  const onChangeIsRead = (event) => {
    console.log(event.target.checked);
    setIsRead(event.target.checked);
    // 1 kere okudutan sonra daha görünmesin
    localStorage.setItem("is_read", "true")
  }

  // input List Clear
  const inputListClear = () => {
    setRegisterNickName(undefined)
    setRegisterName(undefined)
    setRegisterSurname(undefined)
    setRegisterEmail(undefined)
    setRegisterPassword(undefined)
  }

  // OnChange
  const registerNickNameOnChange = (event) => {
    const { name, value } = event.target;
    setRegisterNickName(value);
  }

  const registerNameOnChange = (event) => {
    const { name, value } = event.target;
    setRegisterName(value);
  }

  const registerSurnameOnChange = (event) => {
    const { name, value } = event.target;
    setRegisterSurname(value);
  }

  const registerEmailOnChange = (event) => {
    const { name, value } = event.target;
    setRegisterEmail(value);
  }

  const registerPasswordOnChange = (event) => {
    const { name, value } = event.target;
    setRegisterPassword(value);
  }

  //// SUBMIT
  // registerCreateSubmit
  const registerCreateSubmit = (event) => {

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
              onChange={registerNickNameOnChange} />
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
              onChange={registerNameOnChange} />
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
              onChange={registerSurnameOnChange} />
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
              onChange={registerEmailOnChange} />
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
              onChange={registerPasswordOnChange} />
          </div>
        </div>

        {/* READ */}
        {(localStorage.getItem("is_read") == "true") ? "" :
          <span style={{ display: "inline" }}>
            <input
              type="checkbox"
              className="form-check-input"
              onChange={onChangeIsRead}
              name="isRead"
              id="isRead" /> <abbr title="Register Olurken Kayıt işlemleri">Okudunuz mu ?</abbr> <br />
          </span>
        }

        {/* RESET */}
        <button
          type='reset'
          onClick={inputListClear}
          className="btn btn-danger mt-2 me-2">Temizle</button>

        {/* SUBMIT   */}
        <button
          type='submit'
          onClick={registerCreateSubmit}
          className="btn btn-primary mt-2 me-2"
          disabled={isRead}>Gönder</button>
      </form>
      <br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    </React.Fragment>
  )
}

export default RegisterCreate