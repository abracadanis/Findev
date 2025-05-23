import {Button, FloatingLabel} from "react-bootstrap";
import {Form} from "react-bootstrap";
import { useForm } from 'react-hook-form';
import {ApplicationApi, Configuration} from '../../openapi';

const conf = new Configuration({
    basePath: 'http://localhost:3000/api/findev',
});

const api = new ApplicationApi(conf);

type UserDataInput = {
    name: string;
    surname: string;
}

const NewUserForm = () => {

    const { register, handleSubmit } = useForm<UserDataInput>({
        shouldUseNativeValidation: true
    });

    const onSubmit = async (data: UserDataInput) => {
        api.createUser(data).then();
        console.log("CREATE USER");
    };

    return (
        <div className="w-50 d-inline-block">
            <form onSubmit={handleSubmit(onSubmit)}>
                <FloatingLabel
                    controlId="floatingInput"
                    label="Name"
                    className="mb-3"
                >
                    <Form.Control type="text" {...register("name", { required: "Please enter your first name." })} />
                </FloatingLabel>
                <FloatingLabel controlId="floatingInput" label="Surname">
                    <Form.Control type="text" {...register("surname", { required: "Please enter your second name." })}/>
                </FloatingLabel>
                <Button className="mt-2 mb-0 " as="input" type="submit" value="Add User" />{' '}
            </form>
        </div>
    )
}

export default NewUserForm;