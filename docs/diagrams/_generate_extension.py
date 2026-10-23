# -*- coding: utf-8 -*-
import html, os

FONT = "'PingFang SC','Hiragino Sans GB','Microsoft YaHei',sans-serif"
MONO = "'SF Mono',Menlo,Consolas,'PingFang SC',monospace"
INK, BODY, MUTED = "#0F172A", "#475569", "#64748B"
LINE, BG = "#E2E8F0", "#F6F8FC"
GREEN, TEAL, BLUE, AMBER, GRAY = "#10B981", "#14B8A6", "#0EA5E9", "#F59E0B", "#94A3B8"

def esc(s): return html.escape(str(s), quote=True)
def tw(s, fs): return sum(fs if ord(ch) > 127 else fs * 0.60 for ch in s)
WARN = []
def fit(s, fs, limit, tag=""):
    if tw(s, fs) > limit: WARN.append(f"OVERFLOW {tag}: {s!r} need {tw(s,fs):.0f} > {limit}")

class SVG:
    def __init__(self, w, h):
        self.w, self.h = w, h
        self.body, self.defs = [], []
        self.defs.append('<filter id="sh" x="-20%" y="-20%" width="140%" height="140%"><feDropShadow dx="0" dy="2" stdDeviation="4" flood-color="#0F172A" flood-opacity="0.07"/></filter>')
    def add(self, s): self.body.append(s)
    def rect(self, x, y, w, h, fill="#fff", stroke=LINE, rx=12, sw=1, shadow=False, dash=None):
        sh = ' filter="url(#sh)"' if shadow else ''
        d = f' stroke-dasharray="{dash}"' if dash else ''
        self.add(f'<rect x="{x}" y="{y}" width="{w}" height="{h}" rx="{rx}" fill="{fill}" stroke="{stroke}" stroke-width="{sw}"{sh}{d}/>')
    def line(self, x1, y1, x2, y2, color="#94A3B8", sw=1.5, dash=None, marker=None):
        d = f' stroke-dasharray="{dash}"' if dash else ''
        self.add(f'<line x1="{x1}" y1="{y1}" x2="{x2}" y2="{y2}" stroke="{color}" stroke-width="{sw}"{d} stroke-linecap="round"/>')
    def path(self, d, color=MUTED, sw=1.5, dash=None, marker=None):
        ds = f' stroke-dasharray="{dash}"' if dash else ''
        self.add(f'<path d="{d}" fill="none" stroke="{color}" stroke-width="{sw}"{ds} stroke-linecap="round"/>')
    def circle(self, cx, cy, r, fill): self.add(f'<circle cx="{cx}" cy="{cy}" r="{r}" fill="{fill}"/>')
    def text(self, x, y, s, fs=13, fill=INK, anchor="start", weight=400, family=FONT):
        self.add(f'<text x="{x}" y="{y}" font-family="{family}" font-size="{fs}" fill="{fill}" font-weight="{weight}" text-anchor="{anchor}">{esc(s)}</text>')
    def marker(self, color):
        mid = "m" + color.replace("#", "")
        self.defs.append(f'<marker id="{mid}" viewBox="0 0 10 10" refX="8.5" refY="5" markerWidth="7" markerHeight="7" orient="auto-start-reverse"><path d="M0,0 L10,5 L0,10 z" fill="{color}"/></marker>')
        return mid
    def save(self, path):
        out = [f'<svg xmlns="http://www.w3.org/2000/svg" width="{self.w}" height="{self.h}" viewBox="0 0 {self.w} {self.h}">',
               "<defs>" + "".join(self.defs) + "</defs>",
               f'<rect width="{self.w}" height="{self.h}" fill="{BG}"/>'] + self.body + ["</svg>"]
        open(path, "w", encoding="utf-8").write("\n".join(out))

def node(s, x, y, w, h, num, title, sub, color, fill, dashed=False, tag=None):
    fit(title, 14, w - 60, f"node {title}")
    fit(sub, 11.5, w - 60, f"sub {sub}")
    s.rect(x, y, w, h, fill=fill, stroke=color if color != GRAY else "#CBD5E1", rx=12,
           sw=1.3, shadow=True, dash="6 4" if dashed else None)
    s.circle(x + 22, y + h/2, 14, color)
    s.text(x + 22, y + h/2 + 5, num, fs=12, fill="#fff", anchor="middle", weight=700)
    s.text(x + 48, y + 32, title, fs=14, fill=INK, weight=600, family=MONO)
    s.text(x + 48, y + 54, sub, fs=11.5, fill=BODY)
    if tag:
        tw_ = tw(tag, 10) + 18
        s.rect(x + w - tw_ - 14, y + 12, tw_, 22, fill=color, stroke=color, rx=11)
        s.text(x + w - tw_/2 - 14, y + 27, tag, fs=10, fill="#fff", anchor="middle", weight=600)

s = SVG(1650, 1340)
s.text(70, 56, "扩展点全景：FPP 与 BPP 插在生命周期的哪一步？", fs=30, weight=700, fill=INK)
s.text(70, 88, "一句话：FPP 改“图纸”（BeanDefinition），BPP 改“成品”（Bean 实例）——名字很像，阶段差得很远", fs=14, fill=BODY)
# 图例
s.circle(1290, 50, 5, GREEN); s.text(1302, 54, "FPP 阶段", fs=11.5, fill=MUTED)
s.circle(1390, 50, 5, TEAL); s.text(1402, 54, "BPP 阶段", fs=11.5, fill=MUTED)
s.circle(1490, 50, 5, GRAY); s.text(1502, 54, "待补齐", fs=11.5, fill=MUTED)

# ---------- 左栏：refresh ----------
s.rect(70, 120, 700, 730, fill="#FFFFFF", stroke=LINE, rx=16, shadow=False)
s.add('<rect x="70" y="138" width="5" height="694" rx="2.5" fill="#6366F1"/>')
s.text(100, 158, "容器级：refresh() 的五步", fs=18, weight=700, fill=INK)
s.text(100, 181, "ApplicationContext 启动时只走一遍；顺序由父类模板方法固定", fs=12, fill=MUTED)

ax = 122
s.line(ax, 220, ax, 730, color="#CBD5E1", sw=2)
left = [
    ("1", "refreshBeanFactory()", "读 XML，把 <bean> 翻译成 BeanDefinition 注册进 Map", BLUE, "#FFFFFF", None),
    ("2", "getBeanFactory()", "拿到 DefaultListableBeanFactory（此时它只有图纸）", BLUE, "#FFFFFF", None),
    ("3", "invokeBeanFactoryPostProcessors()", "实例化 FPP，调用 postProcessBeanFactory：改图纸", GREEN, "#F0FDF4", "FPP 入口"),
    ("4", "registerBeanPostProcessors()", "实例化 BPP，addBeanPostProcessor 放进 List：待命", TEAL, "#F0FDFA", "只注册"),
    ("5", "preInstantiateSingletons()", "遍历 beanName，逐个 getBean → 进入右侧流程", BLUE, "#F0F9FF", None),
]
ys = [200, 310, 420, 530, 640]
for i, (n, t, sub, c, f, tag) in enumerate(left):
    y = ys[i]
    s.circle(ax, y + 46, 6, c)
    node(s, 150, y, 590, 92, n, t, sub, c, f, tag=tag)
    if i < 4:
        s.line(445, y + 92, 445, ys[i+1], color="#CBD5E1", sw=1.8, marker=s.marker("#CBD5E1"))

s.rect(100, 758, 640, 68, fill="#FFFBEB", stroke="#FDE68A", rx=10, shadow=False)
s.text(118, 782, "注意两个“还没”", fs=12, fill="#B45309", weight=700)
s.text(118, 802, "FPP 跑完时，任何业务 Bean 都还没创建；BPP 只是登记，还没加工过任何 Bean。", fs=11, fill="#92400E")
fit("FPP 跑完时，任何业务 Bean 都还没创建；BPP 只是登记，还没加工过任何 Bean。", 11, 604, "left note")

# ---------- 右栏：createBean ----------
s.rect(820, 120, 760, 730, fill="#FFFFFF", stroke=LINE, rx=16, shadow=False)
s.add('<rect x="820" y="138" width="5" height="694" rx="2.5" fill="%s"/>' % TEAL)
s.text(850, 158, "Bean 级：createBean() 的内部", fs=18, weight=700, fill=INK)
s.text(850, 181, "每个 Bean 都会完整走一遍；BPP 就夹在“初始化”前后", fs=12, fill=MUTED)

bx = 872
s.line(bx, 220, bx, 760, color="#CBD5E1", sw=2)
right = [
    ("1", "createBeanInstance()", "反射或 CGLIB 造出“裸对象”，属性全是 null", BLUE, "#FFFFFF", None, False),
    ("2", "applyPropertyValues()", "填充属性；遇到 ref 递归 getBean，依赖在这里注入", BLUE, "#FFFFFF", None, False),
    ("3", "BPP before", "postProcessBeforeInitialization：属性已就绪，init 还没跑", TEAL, "#F0FDFA", "改成品①", False),
    ("4", "invokeInitMethods()", "init-method / afterPropertiesSet（本项目空实现）", GRAY, "#F8FAFC", "待补", True),
    ("5", "BPP after", "postProcessAfterInitialization：可返回代理对象", TEAL, "#F0FDFA", "改成品②", False),
    ("6", "addSingleton()", "放入单例池；返回给调用方", BLUE, "#F0F9FF", None, False),
]
ys2 = [200, 296, 392, 488, 584, 680]
for i, (n, t, sub, c, f, tag, dashed) in enumerate(right):
    y = ys2[i]
    s.circle(bx, y + 40, 6, c)
    node(s, 900, y, 650, 80, n, t, sub, c, f, tag=tag, dashed=dashed)
    if i < 5:
        s.line(1225, y + 80, 1225, ys2[i+1], color="#CBD5E1", sw=1.8, marker=s.marker("#CBD5E1"))

s.text(850, 802, "多个 BPP：before / after 都按 List 顺序执行；本项目顺序来自 HashMap，不稳定。", fs=11, fill=MUTED)
fit("多个 BPP：before / after 都按 List 顺序执行；本项目顺序来自 HashMap，不稳定。", 11, 700, "right note")

# ---------- 对比表 ----------
s.text(70, 908, "一张表记住区别", fs=18, weight=700, fill=INK)
ty, rowh = 924, 42
s.rect(70, ty, 1510, 46, fill="#0F172A", stroke="#0F172A", rx=10, shadow=False)
s.text(88, ty + 29, "对比项", fs=12.5, fill="#CBD5E1", weight=600)
s.text(258, ty + 29, "BeanFactoryPostProcessor（FPP）", fs=12.5, fill="#6EE7B7", weight=700)
s.text(928, ty + 29, "BeanPostProcessor（BPP）", fs=12.5, fill="#5EEAD4", weight=700)

rows = [
    ("操作对象", "BeanDefinition（图纸、元数据）", "Bean 实例（已经 new 出来的成品）"),
    ("执行时机", "refresh 第 3 步；所有定义加载完、任何 Bean 实例化前", "refresh 第 4 步注册；每个 Bean 初始化前后回调"),
    ("执行次数", "容器启动时一次（每个 FPP 一次）", "每个 Bean 都执行（N 个 Bean × M 个 BPP）"),
    ("返回值", "void：只能改定义，不能替换对象", "Object：可返回原对象、新对象或代理"),
    ("典型用途", "占位符替换、改属性值、动态注册 BeanDefinition", "AOP 代理、注解处理、字段校验、埋点统计"),
]
for i, (a, b, c) in enumerate(rows):
    y = ty + 46 + i * rowh
    s.rect(70, y, 1510, rowh, fill="#FFFFFF" if i % 2 == 0 else "#F8FAFC", stroke=LINE, rx=0, shadow=False)
    s.text(88, y + 27, a, fs=12, fill=INK, weight=600)
    s.text(258, y + 27, b, fs=11.5, fill=BODY)
    s.text(928, y + 27, c, fs=11.5, fill=BODY)
    fit(b, 11.5, 650, f"row{i}b"); fit(c, 11.5, 640, f"row{i}c")
for vx in (240, 910):
    s.line(vx, ty + 46, vx, ty + 46 + len(rows) * rowh, color=LINE, sw=1)
s.rect(70, ty, 1510, 46 + len(rows) * rowh, fill="none", stroke=LINE, rx=10, shadow=False)

# ---------- 三个误区 ----------
mis = [
    ("误区 1：before 在构造之前？", "不。对象已 new、属性已填，before 只是 init-method 之前。", GREEN),
    ("误区 2：BPP 注册晚点没事？", "有事。注册前创建的 Bean 不会被它加工，所以注册要放在预实例化之前。", TEAL),
    ("误区 3：FPP 里随便 getBean？", "会提前实例化，绕过后面的 BPP，顺序就乱了。只改定义最安全。", AMBER),
]
for i, (t, d, c) in enumerate(mis):
    x = 70 + i * 520
    w = 490 if i < 2 else 490
    s.rect(x, 1200, w, 96, fill="#FFFFFF", stroke=LINE, rx=12, shadow=True)
    s.add(f'<rect x="{x}" y="{1212}" width="4" height="72" rx="2" fill="{c}"/>')
    s.text(x + 18, 1228, t, fs=12.5, fill=INK, weight=700)
    s.text(x + 18, 1252, d, fs=11, fill=BODY)
    fit(d, 11, w - 36, f"mis{i}")

s.save("docs/diagrams/03-extension-points.svg")
print("\n".join(WARN) if WARN else "no overflow warnings")
print("saved", os.path.getsize("docs/diagrams/03-extension-points.svg"))
