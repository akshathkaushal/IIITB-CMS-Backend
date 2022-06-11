FROM alpine

RUN pip3 install docker
COPY inventory /inventory
COPY ansible.cfg /etc/ansible/ansible.cfg
COPY entrypoint.sh /entrypoint.sh

CMD ["sh", "/entrypoint.sh"]