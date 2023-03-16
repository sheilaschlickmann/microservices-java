const amqp = require('amqplib');

const nodemailer = require('nodemailer');

const transport = nodemailer.createTransport({
    host: 'smtp.gmail.com',
    port: 587,
    secure: false,
    auth: {
        user: 'silva.christian@gmail.com',
        pass: 'ccc',
    }
});

const filaRabbitMQ = 'Desastres';

// tenta conectar ao RabbitMQ
amqp.connect({
    host: 'localhost',
    port: 15672,
    username: 'admin',
    password: 123456
})  .then((conexao) => {

    // Cria Canal de Comunicação
    conexao.createChannel()
        .then((canal) => {
            // Consome a fila "Desaster"
            canal.consume(filaRabbitMQ,
                (mensagem) => {

                    {   // Notifica por email o conteudo da fila
                        transport.sendMail({
                            from: 'Notificação de desaster natural <silva.christian@gmail.com>',
                            to: 'silva.christian@gmail.com',
                            subject: 'Notificação de Possível Desastre Natural',
                            html: '<h1>Aviso Urgente!!!</h1> <p>Notificação de possível incidente!</p>',
                            text: 'Aviso Urgente, Notificação de possível incidente!',
                        })
                            .then(() => console.log('Email enviado com sucesso!'))
                            .catch((err) => console.log('Erro ao enviar Email: ', err))
                    }
                    console.log(mensagem.content.toString())
                },
                {
                    noAck: true
                })
        })
        .catch((erro) => console.log(erro))
})
    .catch((erro) => console.log(erro))