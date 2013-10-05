
varying vec3 vNormal;
uniform float g_Time;

void main(){
    vec3 normal = normalize(vNormal);
    vec3 unnormal = 1-normal;
    vec3 color;
    float slow = g_Time / 3;
    float p = mod(slow,1);
    float q = 1-p;
    float t = mod((slow),2);
    if(t < 1){
    	color = p*normal + q*unnormal;
    }else{
    	color = q*normal + p*unnormal;
    }
    gl_FragColor = vec4(0.1+(color.x+1)/2,color.y,color.z,1);
}