# 🎓 Sistema de Reservas Universitário

Este projeto foi desenvolvido como parte da disciplina de Modelagem de Processos no curso de **Gestão da Tecnologia da Informação** da **FATEC Campinas**. O sistema tem como objetivo **automatizar o processo de reservas de laboratórios e equipamentos acadêmicos**, substituindo processos manuais baseados em e-mails, formulários físicos e planilhas.

## 📌 Objetivo

Criar uma aplicação desktop que:
- Centralize e automatize as reservas acadêmicas;
- Reduza conflitos de agendamento e retrabalho;
- Ofereça rastreabilidade e geração de relatórios;
- Melhore a experiência do usuário com notificações e histórico;

---

## 🧱 Tecnologias Utilizadas

- **Java** (com arquitetura MVC)
- **Java Swing** (interface gráfica)
- **Serialização de objetos** (para persistência de dados)

---

## 👥 Perfis de Usuário

- **Aluno**:
  - Cadastro/Login
  - Solicitação de reservas
  - Consulta de status das reservas
- **Coordenador (Laboratorista)**:
  - Visualização das solicitações
  - Aprovação/Recusa com justificativa
  - Geração de relatórios

---

## 🔄 Fluxos Modelados (BPMN)

Foram criados dois fluxos:
- **AS-IS (Atual)**: Processo manual e suscetível a erros
- **TO-BE (Proposto)**: Processo automatizado com validação, notificações e rastreabilidade

---

## ✅ Funcionalidades

- Login e autenticação com perfis distintos
- Solicitação de reservas com validação de datas
- Aprovação ou recusa com justificativa
- Notificações automáticas por e-mail
- Armazenamento dos dados via arquivos serializados
- Testes de funcionalidades com base em cenários Gherkin

---

## 🧪 Exemplos de Testes de funcionalidade 

- ✅ Reserva realizada com sucesso
- ❌ Data retroativa negada com mensagem explicativa
- ❌ Solicitação recusada com justificativa enviada por e-mail

---

## 📈 Resultados

- Redução no tempo de resposta e no retrabalho
- Aumento da transparência e rastreabilidade
- Maior eficiência no uso dos recursos acadêmicos

---

## 🛠️ Possíveis Melhorias Futuras

- Migração para banco de dados relacional (ex: MySQL, PostgreSQL)
- Interface web responsiva e aplicativo mobile
- Painel administrativo para gerenciamento de recursos
- Integração com calendários (Google/Outlook)
- Exportação de relatórios em PDF/CSV
- Criptografia de senhas e melhorias de segurança

---

## 📂 Estrutura do Projeto

```
ProjetoAcademico-SistemaReservas/
│
├── src/
│   ├── controller/         # Controladores (lógica do sistema)
│   ├── model/              # Entidades e lógica de dados
│   └── view/               # Interface gráfica (Java Swing)
│
├── arquivos/               # Dados serializados (.dat)
├── relatorio/              # Documentação do projeto (.pdf, .docx)
├── README.md               # Este arquivo
└── SistemaReservas.jar     # Executável compilado (se disponível)
```

---

## 👩‍💻 Desenvolvedores

- **Murillo Vicentini Domingues** – FATEC Campinas  
- **Stephanie Cucolo Marçula** – FATEC Campinas  
- **Ricardo Ramos da Silva** – FATEC Campinas  

---

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais e está sob a licença MIT.