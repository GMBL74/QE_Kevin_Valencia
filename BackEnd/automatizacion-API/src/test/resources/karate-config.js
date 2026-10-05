function fn() {
  var env = karate.env || 'dev';
  var environments = karate.read('classpath:config/environments.json');
  if (!environments[env]) throw 'Ambiente no configurado: ' + env;
  var config = environments[env];
  karate.configure('connectTimeout', 10000);
  karate.configure('readTimeout', 10); // 10 segundos
  karate.configure('logPrettyRequest', true);
  karate.configure('logPrettyResponse', true);
  return { env: env, baseUrl: config.baseUrl };
}
