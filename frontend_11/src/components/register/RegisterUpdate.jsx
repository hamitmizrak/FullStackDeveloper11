// RegisterUpdate
// REACT
import React, { useEffect, useState } from 'react'

//NAVIGATE
import { useNavigate, useParams } from 'react-router-dom'

//REGISTER API
import RegisterApi from '../../services/RegisterApi';

// I18N
import { withTranslation } from 'react-i18next';

// UPDATE
function RegisterUpdate({ t, i18n, props }) {

  // REDIRECT
  const navigate = useNavigate();

  // STATE
  const [registerNickName, setRegisterNickName] = useState(null);
  const [registerName, setRegisterName] = useState(null);
  const [registerSurname, setRegisterSurname] = useState(null);
  const [registerEmail, setRegisterEmail] = useState(null);
  const [registerPassword, setRegisterPassword] = useState(null);
  const [registerIsPassive, setRegisterIsPassive] = useState(false);

  // STATE ID
  const [registerId, setRegisterId] = useState(null);

  // PARAMS
  const updateParamsRegisterId = useParams();

  //  ERROR, MULTIPLEREQUEST, READ, SPINNER
  const [error, setError] = useState(undefined);
  const [multipleRequest, setMultipleRequest] = useState(false);
  const [isRead, setIsRead] = useState(false);
  const [spinner, setSpinner] = useState(false);

  // USE EFFECT
  useEffect(() => {
    // Params ID
    setRegisterId(updateParamsRegisterId.id);
    // FIND
    RegisterApi.registerApiFindById(updateParamsRegisterId.id)
      .then((response) => {
        if (response.status === 200) {
          console.log(response);
          setRegisterNickName(response.data.registerNickName);
          setRegisterName(response.data.registerName)
          setRegisterSurname(response.data.registerSurname)
          setRegisterEmail(response.data.registerEmail)
          setRegisterPassword(response.data.registerPassword)
          setRegisterIsPassive(response.data.registerIsPassive)
        } else
          Promise.reject()
      })
      .catch((err) => {
        console.error(err)
      })

    // başlangıçta Hatayı gösterme
    setError(undefined);
    setIsRead(false);
    setSpinner(false);
  }, [])

  // FUNCTION
  // Read On Change
  const onChangeIsRead = (event) => {
    //console.log(event.target.checked);
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
    //console.log(name + " " + value);
    setRegisterNickName(value);
  }

  const registerNameOnChange = (event) => {
    const { name, value } = event.target;
    //console.log(`${name} => ${value}`);
    setRegisterName(value);

  }

  const registerSurnameOnChange = (event) => {
    const { name, value } = event.target;
    //console.log(name + " " + value);
    setRegisterSurname(value);
  }

  const registerEmailOnChange = (event) => {
    const { name, value } = event.target;
    //console.log(name + " " + value);
    setRegisterEmail(value);
  }

  const registerPasswordOnChange = (event) => {
    const { name, value } = event.target;
    //console.log(name + " " + value);
    setRegisterPassword(value);
  }

  // onSubmitSearch
  const onSubmitForm = (e) => {
    e.preventDefault();
  }

  //// SUBMIT
  // registerUpdateSubmit
  const registerUpdateSubmit = async (event) => {
    // Register Object
    const registerCreateObject = {
      registerNickName,
      registerName,
      registerSurname,
      registerEmail,
      registerPassword,
      registerIsPassive,
    }
    //console.log(registerCreateObject);

    // Hataları gösterme
    setError(null);

    // Spinner Aktif et
    setSpinner(true);

    // MultipleRequest (Aktif)
    setMultipleRequest(true);

    // API
    try {
      const response = await RegisterApi.registerApiCreate(registerCreateObject);
      if (response.status == 200) {
        // Spinner Pasif et
        setSpinner(false);
        // MultipleRequest (Aktif)
        setMultipleRequest(false);
        // Toast Message
        alert("Kayıt Başarılı");
        navigate('/register/list');
      }
    } catch (err) {
      //console.error(err.response.data.validationErrors);
      setError(err.response.data.validationErrors)
      // Spinner Pasif et
      setSpinner(true);
      // MultipleRequest (Aktif)
      setMultipleRequest(false);
    }
  }

  // Spinner
  const spinnerFunction = () => {
    if (spinner) {
      return (
        <div class="spinner-border  spinner-border-sm text-warning me-2" role="status" >
        </div>
      )
    } else {
      return "";
    }
  }

  //Error
  const classNameData = { error } ? "is-invalid form-control mb-3" : "form-control mb-3";
  //console.log(error);
  //console.log(registerSurname);
  //console.log(classNameData);

  // RETURN
  return (
    <React.Fragment>
      <h1>{t('register_update')}</h1>
      <form onSubmit={onSubmitForm}>
        {/* <form onSubmit="event.preventDefault()"> */}
        <div className="d-grid gap-4">
          {/* NICKNAME */}
          <div className="form-group"><label htmlFor="registerNickName">{t('user_nickname')}</label>
            <input
              type="text"
              className='form-control'
              id="registerNickName"
              name="registerNickName"
              placeholder='registerNickName'
              autoFocus={true}
              required={true}
              // onChange={registerNickNameOnChange}
              onChange={
                (event) => {
                  const { name, value } = event.target;
                  console.log(`${name} => ${value}`);
                  setRegisterNickName(event.target.value);
                }
              }
              value={registerNickName}
            />

            {/* ALERT ERROR */}
            {/* {
                errorAlert(registerNickName)
              } */}

            {
              error ?
                <div className="alert alert-danger">
                  {error.registerNickName}
                </div>
                : ""
            }
          </div>

          {/* registerName */}
          <div className="form-group"><label htmlFor="registerName">{t('user_name')}</label>
            <input
              type="text"
              className='form-control'
              id="registerName"
              name="registerName"
              placeholder='registerName'
              autoFocus={false}
              required={true}
              onChange={registerNameOnChange}
              value={registerName}
            />
            {
              error ?
                <div className="alert alert-danger">
                  {error.registerName}
                </div>
                : ''
            }
          </div>

          {/* registerSurname */}
          <div className="form-group"><label htmlFor="registerSurname">{t('user_surname')}</label>
            <input
              type="text"
              className='form-control'
              id="registerSurname"
              name="registerSurname"
              placeholder='registerSurname'
              autoFocus={false}
              required={true}
              onChange={registerSurnameOnChange}
              value={registerSurname}
            />
            {
              error ?
                <div className="alert alert-danger" role="alert">
                  {error.registerSurname}
                </div>
                : ''
            }
          </div>

          {/* registerEmail */}
          <div className="form-group"><label htmlFor="registerEmail">{t('user_email')}</label>
            <input
              type="email"
              className='form-control'
              id="registerEmail"
              name="registerEmail"
              placeholder='registerEmail'
              autoFocus={false}
              required={true}
              onChange={registerEmailOnChange}
              value={registerEmail}
            />
            {
              error ?
                <div className="alert alert-danger" role="alert">
                  {error.registerEmail}
                </div>
                : ''
            }
          </div>

          {/* registerPassword */}
          <div className="form-group"><label htmlFor="registerPassword">{t('user_password')}</label>
            <input
              type="text"
              className='form-control'
              id="registerPassword"
              name="registerPassword"
              placeholder='registerPassword'
              autoFocus={false}
              required={true}
              onChange={registerPasswordOnChange}
              value={registerPassword}
            />
            {
              error ?
                <div className="alert alert-danger" role="alert">
                  {error.registerPassword}
                </div>
                : ''
            }
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
              id="isRead" />
            <abbr title="Register Olurken Kayıt işlemleri" htmlFor="isRead">{t('is_read')}</abbr>
            <br />
          </span>
        }

        {/* RESET */}
        <button
          type='reset'
          onClick={inputListClear}
          className="btn btn-danger mt-2 me-2">{t('cleaner')}</button>

        {/* SUBMIT   */}
        <button
          type='submit'
          onClick={registerUpdateSubmit}
          className="btn btn-primary mt-2 me-2"
          disabled={(!localStorage.getItem("is_read") == true) || (multipleRequest)}>
          {/* SPINNER */}
          {
            spinnerFunction()
          }
          {t('submit')}
        </button>
      </form>
    </React.Fragment>
  )// end return
}// end function

// Export i18n Wrapper
export default withTranslation()(RegisterUpdate) 