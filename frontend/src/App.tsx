import './App.scss';

import Navbar from './components/Navbar';
import ProjectList from './components/ProjectList/ProjectList';
import React, { useEffect, useState } from 'react';
import {ApplicationApi, Configuration, ProjectSo, UserSo} from './openapi';
import {createBrowserRouter, RouterProvider, Route, BrowserRouter, Routes} from "react-router-dom";
import MainPage from "./components/MainPage";
import ErrorPage from "./components/ErrorPage";
import UserList from "./components/UserList/UserList";

const conf = new Configuration({
    basePath: 'http://localhost:3000/api/findev',
});

const api = new ApplicationApi(conf);

const App = () => {

    const [projects, setProjects] = useState<ProjectSo[]>([]);
    const [users, setUsers] = useState<UserSo[]>([]);

    useEffect(() => {
        api.getProjects().then((data) => {
            setProjects(data);
        });
    }, [projects]);

    useEffect(() => {
        api.getUsers().then((usersData) => {
            setUsers(usersData);
        })
    }, [])

    let props = {
        project: projects,
        user: users
    }

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
                        element={<div>
                                    <Navbar/>
                                    <ProjectList {...props}/>
                                </div>}>
                    </Route>

                    <Route
                        path = "/users"
                        element={<div>
                            <Navbar/>
                            <UserList user={users}/>
                        </div>}>
                    </Route>

                </Routes>
            </BrowserRouter>

        </div>
    );
};

export default App;
