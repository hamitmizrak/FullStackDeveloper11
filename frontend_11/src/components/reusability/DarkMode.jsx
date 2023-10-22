import React, { useState } from 'react';
import { DarkModeToggle } from '@anatoliygatt/dark-mode-toggle';


// import { Link } from 'react-router-dom';
// import { DarkModeSwitch } from 'react-toggle-dark-mode';

// npm install react-toggle-dark-mode
// npm install @anatoliygatt/dark-mode-toggle @emotion/react @emotion/styled
// Function
function DarkMode() {

    const [mode, setMode] = useState('dark');
    //1 YOL
    // const[darkMode,setDarkMode]=React.useState(false);
    // const toggleDarkMode=(checked)=>{
    //     setDarkMode(checked)
    // };


    return (
        <React.Fragment>
            {/* Register / Login */}
            <ul className="navbar-nav ms-auto mt-2 mt-lg-0">

                {/* <li className="nav-item">
                    <DarkModeSwitch
                    style={{marginTop:'1rem',color:"white"}}
                    checked={darkMode}
                    onChange={toggleDarkMode}
                    size={40}
                    />
               </li>  */}

                <li>
                    <DarkModeToggle
                        mode={mode}
                        dark="Dark"
                        light="Light"
                        size="sm"
                        inactiveTrackColor="#e2e8f0"
                        inactiveTrackColorOnHover="#f8fafc"
                        inactiveTrackColorOnActive="#cbd5e1"
                        activeTrackColor="#334155"
                        activeTrackColorOnHover="#1e293b"
                        activeTrackColorOnActive="#0f172a"
                        inactiveThumbColor="#1e293b"
                        activeThumbColor="#e2e8f0"
                        onChange={(mode) => {
                            setMode(mode);
                        }}
                    />
                </li>

            </ul>
        </React.Fragment>
    )
}

export default DarkMode