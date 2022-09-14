import {createBrowserRouter, RouterProvider} from "react-router-dom";
import Navbar from "./Navbar";
import ProjectList from "./ProjectList/ProjectList";
import React, {useState} from "react";
import {ProjectSo} from "../openapi";

const MainPage = () => {

    return (
        <div>
            <Navbar/>
            <div className="text-center">
                <p className = "fs-1 m-4">! Welcome page !</p>
                <p className = "fs-5 fw-lighter"> ... some images ... </p>
                <p className= "fs-2"> ... some text about us ... </p>
                <p className = "fs-5 fw-lighter"> ... some images again ... </p>
            </div>
        </div>
    );
}

export default MainPage;