FROM alpine

COPY inventory /inventory
COPY ../../ansible/playbook.yml /playbook.yml
COPY ansible.cfg /etc/ansible/ansible.cfg
COPY entrypoint.sh /entrypoint.sh

CMD ["sh", "/entrypoint.sh"]