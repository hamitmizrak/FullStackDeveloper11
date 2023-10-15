import React, { Component } from 'react';
import OtherLanguageReusability from '../internationalization/OtherLanguageReusability';
import { withTranslation } from 'react-i18next';
import { Link } from 'react-router-dom';

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
                        <Link className="navbar-brand" to="/"><i className={this.props.logo}></i> </Link>

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
                                <Link className="nav-link active" to="/"><i className="fa-solid fa-house-chimney"></i> {t('home')} </Link>
                                </li>
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
                                        <Link className="dropdown-item" to="/login" >{t('login')} </Link>
                                        <Link className="dropdown-item" to="/register/create" >{t('register')} </Link>
                                    </div>
                                </li>

                                <li className="nav-item dropdown">
                                    <a
                                        className="nav-link dropdown-toggle"
                                        href="#"
                                        id="dropdownId"
                                        data-bs-toggle="dropdown"
                                        aria-haspopup="true"
                                        aria-expanded="false"
                                    >
                                        {t('registers')}
                                    </a>
                                    
                                    <div className="dropdown-menu" aria-labelledby="dropdownId">
                                        <Link className="dropdown-item" to="/register/list" >{t('register_list')} </Link>
                                        <Link className="dropdown-item" to="/register/create" >{t('register_create')} </Link>
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