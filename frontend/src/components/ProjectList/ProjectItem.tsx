import {ApplicationApi, Configuration, ProjectSo} from '../../openapi';
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";

import "./ProjectItem.css";

const conf = new Configuration({
    basePath: 'http://localhost:3000/api/findev',
});

const api = new ApplicationApi(conf);

const ProjectItem = (props: ProjectSo) => {
    let infoUrl = props.id

    const [image, setImage] = useState<string | undefined>();

    useEffect(() => {
        api.getImage(props.imageId)
            .then(blob => {
                setImage(URL.createObjectURL(blob))
            })
    }, []);


    return (
        <div className="card text-light bg-dark w-25 h-100 m-4 text-break">
            {props.imageId === undefined && <img src="https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png" alt="..."/>}
            {props.imageId && <img src={image} alt="..."/>}
            <div className="card-body">
                <h5 className="card-title">{props.title} #{props.id}</h5>
                <p className="card-text">{props.description}</p>
                <Link to={infoUrl.toString()} className="btn btn-primary">Info</Link>
            </div>
        </div>
    )
}

export default ProjectItem;