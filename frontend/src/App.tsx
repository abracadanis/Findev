import './App.scss';

import Navbar from './components/Navbar';
import ProjectList from './components/ProjectList/ProjectList';
import React, { useEffect, useState } from 'react';
import { ApplicationApi, Configuration, ProjectSo } from './openapi';

const conf = new Configuration({
    basePath: 'http://localhost:3000/api/findev',
});

const api = new ApplicationApi(conf);

const App = (props: any) => {
    useEffect(() => {
        api.getProjects().then((data) => {
            setProjects(data);
        });
    }, []);

    const [projects, setProjects] = useState<ProjectSo[]>([]);

    // const projects = [
    //     {"id": 123, "title": "title", "description": "description"},
    //     {"id": 321, "title": "title11", "description": "description11"},
    // ]

    return (
        <div>
            <Navbar />
            <ProjectList project={projects} />
        </div>
    );
};

export default App;
