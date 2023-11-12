// RegisterUpdate
// REACT
import React, { useEffect, useState } from 'react'

//NAVIGATE
import {Link, useNavigate, useParams} from 'react-router-dom'

// REGISTER
import RegisterRolesApi from '../../services/RegisterRolesApi';

// I18N
import { withTranslation } from 'react-i18next';

// UPDATE
function RegisterUpdate({ t, i18n, props }) {

  // REDIRECT
  const navigate = useNavigate();

  // STATE
  const [roleName, setRoleName] = useState(null);

  //  ERROR, MULTIPLEREQUEST, READ, SPINNER
  const [error, setError] = useState(undefined);
  const [multipleRequest, setMultipleRequest] = useState(false);
  const [spinner, setSpinner] = useState(false);

  // STATE ID
  const [registerId, setRegisterId] = useState(null);

  // PARAMS
  const updateParamsRegisterId = useParams();

  // USE EFFECT
  useEffect(() => {
    // Params ID
    setRegisterId(updateParamsRegisterId.id);
    // FIND
    RegisterRolesApi.registerRolesFindByIdApi(updateParamsRegisterId.id)
      .then((response) => {
        if (response.status === 200) {
          console.log(response);
          setRoleName(response.data.roleName);
        } else
          Promise.reject()
      })
      .catch((err) => {
        console.error(err)
      })

    // başlangıçta Hatayı gösterme
    setError(undefined);
    setSpinner(false);
  }, [])

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
  // registerUpdateSubmit
  const roleUpdateSubmit = async (event) => {
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
        alert("Güncelleme Başarılı");
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
          <div className="form-group"><label htmlFor="registerNickName">{t('role_name')}</label>
            <input
              type="text"
              className='form-control'
              id="roleName"
              name="roleName"
              placeholder='roleName'
              autoFocus={true}
              required={true}
              // onChange={registerNickNameOnChange}
              onChange={
                (event) => {
                  const { name, value } = event.target;
                  console.log(`${name} => ${value}`);
                  setRoleName(event.target.value);
                }
              }
              value={roleName}
            />

            {/* ALERT ERROR */}
            {/* {
                errorAlert(registerNickName)
              } */}

            {
              error ?
                <div className="alert alert-danger">
                  {error.roleName}
                </div>
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
          onClick={roleUpdateSubmit}
          className="btn btn-primary mt-2 me-2"
          disabled={(multipleRequest)}>
          {/* SPINNER */}
          {
            spinnerFunction()
          }
          {t('submit')}
        </button>

       {/* ROLE LIST*/}
        <Link to="/role/list" className="btn btn-warning">Role List</Link>
      </form>
    </React.Fragment>
  )// end return
}// end function

// Export i18n Wrapper
export default withTranslation()(RegisterUpdate) 