import './App.scss';

import Navbar from './components/Navbar';
import ProjectList from './components/ProjectList/ProjectList';
import React, { useEffect, useState } from 'react';
import {ApplicationApi, Configuration, ProjectSo, UserSo} from './openapi';
import {Route, BrowserRouter, Routes, Link} from "react-router-dom";
import MainPage from "./components/MainPage";
import ErrorPage from "./components/ErrorPage";
import UserList from "./components/UserList/UserList";
import ProjectInfo from "./components/ProjectInfo";
import ProjectsPage from "./ProjectsPage";
import useApiHook from "./hooks/useApiHook";

const App = () => {

    return (
        <div>
            <BrowserRouter>
                <Routes>

                    <Route
                        path = "/home"
                        element={<MainPage/>}>
                    </Route>

                    <Route
                        path = "/projects"
                        element={<ProjectsPage/>}>
                    </Route>

                    <Route
                        path = "/users"
                        element={<div>
                            <Navbar/>
                            <UserList/>
                        </div>}>
                    </Route>

                    <Route
                        path = "/projects/:id"
                        element={<ProjectInfo/>}>
                    </Route>

                </Routes>

            </BrowserRouter>

        </div>
    );
};

export default App;
