import React, { Component } from 'react';
import OtherLanguageReusability from '../internationalization/OtherLanguageReusability';
import { withTranslation } from 'react-i18next';
import { Link } from 'react-router-dom';
import LoginPage from './login/LoginPage';


class Header extends Component {

    // Display Name
    static displayName = "HeaderProject";

    // CONSTRUCTOR
    constructor(props) {
        super(props);

        // STATE
        this.state = {

        }

        // BIND
    }

    // CDM

    //FUNCTION

    // RENDER
    render() {

        // object destructing
        const { t } = this.props;

        // RETURN
        return (
            <React.Fragment>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
                    <div className="container">
                        <a className="navbar-brand" href="#">
                            <i className={this.props.logo}></i>
                        </a>
                        <button
                            className="navbar-toggler d-lg-none"
                            type="button"
                            data-bs-toggle="collapse"
                            data-bs-target="#collapsibleNavId"
                            aria-controls="collapsibleNavId"
                            aria-expanded="false"
                            aria-label="Toggle navigation"
                        >
                            <span className="navbar-toggler-icon" />
                        </button>
                        <div className="collapse navbar-collapse" id="collapsibleNavId">
                            <ul className="navbar-nav me-auto mt-2 mt-lg-0">
                                <li className="nav-item">
                                    <a className="nav-link active" href="#" aria-current="page">
                                        {t("home")} <span className="visually-hidden">(current)</span>
                                    </a>
                                </li>
                                {/* <li className="nav-item">
                                    <a className="nav-link" href="#">
                                        Link
                                    </a>
                                </li> */}
                                {/* <li className="nav-item dropdown">
                                    <a
                                        className="nav-link dropdown-toggle"
                                        href="#"
                                        id="dropdownId"
                                        data-bs-toggle="dropdown"
                                        aria-haspopup="true"
                                        aria-expanded="false"
                                    >
                                        Dropdown
                                    </a>
                                    <div className="dropdown-menu" aria-labelledby="dropdownId">
                                        <a className="dropdown-item" href="#">
                                            Action 1
                                        </a>
                                        <a className="dropdown-item" href="#">
                                            Action 2
                                        </a>
                                    </div>
                                </li> */}
                            </ul>




                            {/* Register / Login */}
                            <ul className="navbar-nav ms-auto mt-2 mt-lg-0">
                                {/* i18n Language */}
                                <OtherLanguageReusability />

                                <form className="d-flex my-2 my-lg-0 ">
                                    <input
                                        className="form-control me-sm-2"
                                        type="text"
                                        placeholder={t('search')}
                                    />
                                    <button className="btn btn-outline-success my-2 my-sm-0" type="submit">
                                        {t('search')}
                                    </button>
                                </form>

                                <li className="nav-item dropdown">
                                    <a
                                        className="nav-link dropdown-toggle"
                                        href="#"
                                        id="dropdownId"
                                        data-bs-toggle="dropdown"
                                        aria-haspopup="true"
                                        aria-expanded="false"
                                    >
                                        {t('login')}
                                    </a>
                                    <div className="dropdown-menu" aria-labelledby="dropdownId">

                                        <Link className="dropdown-item" to="/LoginPage" >{t('login')} </Link>

                                        <a className="dropdown-item" href="#">
                                            {t('register')}
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>

            </React.Fragment>
        );
    }
}

// Wrapper High Order (i18n)
export default withTranslation()(Header);