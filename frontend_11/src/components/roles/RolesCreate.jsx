// REACT
import React, { useEffect, useState } from 'react'

// NAVIGATE
import {Link, useNavigate} from 'react-router-dom'

// REGISTER
import RegisterRolesApi from '../../services/RegisterRolesApi';

// I18N
import { withTranslation } from 'react-i18next';

// FUNcTION REGISTER
function RegisterCreate({ t, i18n, props }) {

  // REDIRECT
  const navigate = useNavigate();

  // STATE
  const [roleName, setRoleName] = useState(null);

  //  ERROR, MULTIPLEREQUEST, READ, SPINNER
  const [error, setError] = useState(undefined);
  const [multipleRequest, setMultipleRequest] = useState(false);
  const [spinner, setSpinner] = useState(false);

  // FUNCTION
  // input List Clear
  const inputListClear = () => {
    setRoleName(undefined)
  }


  // onSubmitSearch
  const onSubmitForm = (e) => {
    e.preventDefault();
  }

  //// SUBMIT
  // registerCreateSubmit
  const roleCreateSubmit = async (event) => {
    // Register Object
    const roleCreateObject = {
      roleName
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
      const response = await RegisterRolesApi.registerRolesCreateApi(roleCreateObject);
      if (response.status == 200) {
        // Spinner Pasif et
        setSpinner(false);
        // MultipleRequest (Aktif)
        setMultipleRequest(false);
        // Toast Message
        alert("Kayıt Başarılı");
        navigate('/role/list');
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
        <div className="spinner-border  spinner-border-sm text-warning me-2" role="status" >
        </div>
      )
    } else {
      return "";
    }
  }

  //Error
  const classNameData ={error}  ? "is-invalid form-control mb-3" : "form-control mb-3";
  //console.log(error);
  //console.log(registerSurname);
  //console.log(classNameData);

  // RETURN
  return (
    <React.Fragment>
      <h1 className="mt-5">{t('register_create')}</h1>
      <form onSubmit={onSubmitForm}>
        {/* <form onSubmit="event.preventDefault()"> */}
        <div className="d-grid gap-4">
          {/* NICKNAME */}
          <div className="form-group"><label htmlFor="registerNickName"> {t('register_nick_name')}</label>
            <input
              type="text"
              className={classNameData}
              id="roleName"
              name="roleName"
              placeholder={t('role_name')}
              autoFocus={true}
              required={true}
              // onChange={registerNickNameOnChange}
              onChange={
                (event) => {
                  const { name, value } = event.target;
                  //console.log(`${name} => ${value}`);
                  setRoleName(event.target.value);
                }
              }
            />

            {/* ALERT ERROR */}
            {/* {
                errorAlert(registerNickName)
              } */}

            {
              error ?
                  <div className="invalid-feedback">{error.roleName}</div>
                : ""
            } 
          </div>
        </div>


        {/* RESET */}
        <button
          type='reset'
          onClick={inputListClear}
          className="btn btn-danger mt-2 me-2">{t('cleaner')}</button>

        {/* SUBMIT   */}
        <button
          type='submit'
          onClick={roleCreateSubmit}
          className="btn btn-primary mt-2 me-2"
          disabled={ (multipleRequest)}>

          {/* SPINNER */}
          {
            spinnerFunction()
          }
          {t('submit')}
        </button>

        {/* ROLE LIST*/}
        <Link to="/role/list" className="btn btn-warning mt-2">Role List</Link>

      </form>
      <br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    </React.Fragment>
  ) // end return
}// end functıon


// Export i18n Wrapper
export default withTranslation()(RegisterCreate) 