import { ProjectSo } from '../../openapi';

const ProjectItem = (props: ProjectSo) => {
    return (
        <div className="card text-light bg-dark w-25 h-100 m-4 text-break">
            <img src="https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png" className="card-img-top" alt="..."/>
                <div className="card-body">
                    <h5 className="card-title">{props.title} #{props.id}</h5>
                    <p className="card-text">{props.description}</p>
                    <a href='/projects/:props.id' className="btn btn-primary">Go somewhere</a>
                </div>
        </div>
    )
}

export default ProjectItem;