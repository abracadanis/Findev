import {createBrowserRouter} from "react-router-dom";
import React from "react";
import ProjectList from "./ProjectList/ProjectList";
import {ProjectSo} from "../openapi";



const Navbar = () => {

    return <div>

        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample08"
                    aria-controls="navbarsExample08" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse justify-content-md-center" id="navbarsExample08">
                <ul className="navbar-nav">
                    <li>
                        <a className="nav-link" href= "/home">Home</a>
                    </li>
                    <li className="nav-item active">
                        <a className="nav-link" href= "/projects">Projects</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="/users">Users</a>
                    </li>
                    <li className="nav-item dropdown">
                        <a className="nav-link dropdown-toggle" href="http://example.com" id="dropdown08"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
                        <div className="dropdown-menu" aria-labelledby="dropdown08">
                            <a className="dropdown-item" href="#">Action</a>
                            <a className="dropdown-item" href="#">Another action</a>
                            <a className="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>

    </div>
}

export default Navbar;