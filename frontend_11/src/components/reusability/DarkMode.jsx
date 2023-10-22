import React from 'react'
import { Link } from 'react-router-dom';

// Function
function DarkMode() {

    // Css styleDarkMode
    const styleDarkMode = () => {
        // css
        const css_dark = {
            backgroundColor: "#000000",
            color: "#ffffff",
        }

        let lightDarkMode = document.body.classList.toggle("css_dark")
        console.log("css_dark");
    };

    return (
        <React.Fragment>
            {/* Register / Login */}
            <ul className="navbar-nav ms-auto mt-2 mt-lg-0">

                <li className="nav-item">
                    {/* Root: relative Path */}
                    <Link className="nav-link" onClick={styleDarkMode}><i className="fa-solid fa-circle-half-stroke"></i> </Link>
                </li>
            </ul>
        </React.Fragment>
    )
}

export default DarkMode